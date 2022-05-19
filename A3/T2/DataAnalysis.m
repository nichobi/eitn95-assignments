path = './results/';
imageFile = sprintf('./results/Plot.png');
hold on;

for i=1:3
    dataFile = sprintf('./results/Task-%d.txt', i);
    t = load(dataFile);

    plot(t(:, 1), t(:, 2)/sum(t(:, 2)));
end
xlim([1, 60]);
xlabel('Time spent interacting with one specific person');
ylabel('Portion of interactions');
legend({'V = 2','V = 4','V = U[1-7]'},'Location','northeast')
saveas(gcf, imageFile);
