%% 1a 1b
x1 = [0.153 0.187 0.170 0.134 0.104 0.079 0.054 0.038 0.025 0.016]
x2 = [0.378 0.629 0.776 0.868 0.917 0.948 0.969 0.981 0.989 0.993]
y = [1000 2000 3000 4000 5000 6000 7000 8000 9000 10000]

errorbar(y,x1,repelem([0.002], 10), '+')
hold on
errorbar(y,x2,repelem([0.002], 10), '+')

xlim([0, 11000])
ylim([0, 1.2])
%set(gca, 'XTick', 0:5000:25000)
%set(gca, 'XTickLabel', 0:5000:25000)
%ylabel('Months')
%xlabel('Monthly savings')

title('sdsda')

%% 1c
x1 = [0.181 0.279 0.331 0.357 0.368 0.372 0.365 0.359 0.351 0.336]
x2 = [0.121 0.218 0.291 0.368 0.431 0.474 0.527 0.565 0.597 0.635]
y = [1000 2000 3000 4000 5000 6000 7000 8000 9000 10000]

errorbar(y,x1,repelem([0.002], 10), '+')
hold on
errorbar(y,x2,repelem([0.002], 10), '+')

xlim([0, 11000])
ylim([0, 1.2])
%set(gca, 'XTick', 0:5000:25000)
%set(gca, 'XTickLabel', 0:5000:25000)
%ylabel('Months')
%xlabel('Monthly savings')

title('sdsda')

%%
x = [0.255 0.282 0.298 0.311 0.319 0.323]
y = [6000 7000 8000 9000 10000 11000]

errorbar(y,x,repelem([0.002], 6), '+')


xlim([5000, 12000])
ylim([0, 0.5])
%set(gca, 'XTick', 0:5000:25000)
%set(gca, 'XTickLabel', 0:5000:25000)
%ylabel('Months')
%xlabel('Monthly savings')

title('sdsda')