path = './results/';

for i=1:6
    dataFile = sprintf('./results/Task-%d.txt', i);
    imageFile = sprintf('./results/Task-%d.png', i);
    
    t = load(dataFile);

    figure;
    plot(t(:, 2));
    if i == 4
        % Plot 4 y range differs from 5-6 without this
        ylim([0 70]);
    end
    saveas(gcf, imageFile);
    
    if i >= 4
        % Do statistics on measurements 4-6
        if i == 4
            T = 4;
            M = 1000;
        elseif i == 5
            T = 1;
            M = 4000;
        else
            T = 4;
            M = 4000;
        end
        
        [min, max] = thresholds(95, t(:, 2), i);
        l = max-min;
        fprintf('Interval length for test %d (T=%d, M=%d): %d \n', ...
            i, T, M, l);
    end
end

% Calculates the thresholds for a given set of measurements, then plots
% the probability along with the threshold.
function [min, max] = thresholds(percentile, A, i)
    imageFile = sprintf('./results/Task-%d-threshold.png', i);

    srtd = sort(A);
    [M, ~] = size(srtd);
    remove = round(M*(100-percentile)/200);
    min = srtd(remove);
    max = srtd(M-remove);
    
    figure;
    plot(srtd);
    yline(min, 'r');
    yline(max, 'r');
    saveas(gcf, imageFile)
end
