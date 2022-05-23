path = './results/';

for i=1:6
    dataFile = sprintf('./results/Task-%d.txt', i);
    imageFile = sprintf('./results/Task-%d.png', i);
    
    t = load(dataFile);

    figure;
    plot(t(:, 2));
    if i >= 4
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
        
        l = thresholds(t(:, 2));
        fprintf('Interval length for test %d (T=%d, M=%d): %d \n', ...
            i, T, M, l);
    end
end

% Calculates the thresholds for a given set of measurements, then plots
% the probability along with the threshold.
function [width] = thresholds(A)
    confLevel = 1.96; % For 95%
    size = length(A);
    m = mean(A);
    
    sqDiff = 0;
    for i=1:size
        sqDiff = sqDiff + ((A(i) - m)^2);
    end
    var = sqDiff/size;
    std = sqrt(var);
    width = confLevel * std/sqrt(size);
end
