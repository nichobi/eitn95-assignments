%% Exercise 1
clc;
clear all;

%% Task 1

c = [13, 11];
A = [4, 5; 5, 3; 1, 2];
b = [1500; 1575; 420];
lb = [0; 0];

options = optimoptions('linprog', 'Display', 'off');
[x, result, ~, ~] = linprog(-c, A, b, [], [], lb, [], options);

disp('Maximizing solution for x in task 1:');
disp(x);
disp('Resulting maximum value:');
disp(-result);

%% Task 2

c = [1500, 1575, 420];
A = [4, 5, 1; 5, 3, 2];
b = [13; 11];
lb = [0; 0; 0];

options = optimoptions('linprog', ...
    'Algorithm', 'dual-simplex', ...
    'Display', 'off');
[y, result, ~, ~, ~] = linprog(c, -A, -b, [], [], lb, [], options);

disp('Minimizing solution for y in task 2:');
disp(y);
disp('Resulting minimum value:');
disp(result);
