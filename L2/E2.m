%% Exercise 2
clc;
clear all;

%% Task 1

c = [1, 1, 1, 1, 1, 1, 1];
A = [ ...
    1, 0, 0, 1, 1, 1, 1; ...
    1, 1, 0, 0, 1, 1, 1; ...
    1, 1, 1, 0, 0, 1, 1; ...
    1, 1, 1, 1, 0, 0, 1; ...
    1, 1, 1, 1, 1, 0, 0; ...
    0, 1, 1, 1, 1, 1, 0; ...
    0, 0, 1, 1, 1, 1, 1; ...
];
b = [8; 6; 5; 4; 6; 7; 9];
lb = [0; 0; 0; 0; 0; 0; 0];
intcon = [1, 2, 3, 4, 5, 6, 7];

options = optimoptions('intlinprog', 'Display', 'off');
[x, result, ~, ~] = intlinprog(c, intcon, -A, -b, [], [], lb, [], options);

disp('Minimizing solution for x in task 1:');
disp(x);
disp('Resulting minimum value:');
disp(result);

%% Task 2

c = [1, 1, 1, 1, 1, 1, 1];
A = [ ...
    1, 0, 0, 1, 1, 1, 1; ...
    1, 1, 0, 0, 1, 1, 1; ...
    1, 1, 1, 0, 0, 1, 1; ...
    1, 1, 1, 1, 0, 0, 1; ...
    1, 1, 1, 1, 1, 0, 0; ...
    0, 1, 1, 1, 1, 1, 0; ...
    0, 0, 1, 1, 1, 1, 1; ...
];
b = [8; 6; 5; 4; 6; 7; 9];
lb = [0; 0; 0; 0; 0; 0; 0];

options = optimoptions('linprog', ...
    'Algorithm', 'dual-simplex', ...
    'Display', 'off');
[x, result, ~, ~, ~] = linprog(c, -A, -b, [], [], lb, [], options);

disp('Minimizing solution for x in task 2:');
disp(x);
disp('Resulting minimum value:');
disp(result);
