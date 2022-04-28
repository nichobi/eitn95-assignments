# Results for Task 2

- 1. Approximately 128.39667
- 2. Approximately 6.28908
- 3. Approximately 4.41145
- 4. 
  - 1) When a queue of A-jobs form (>1 customer) in the first example the delay is exactly 1 second. This means that in 1 second the exact same queue will appear but with B-jobs instead. These take 0.02s longer to finish so there will by definition be a new queue and delay.
  - 2) In the second example and the case of a queue of A-jobs the delay varies. This means that the chanc that the new B-jobs will be inserted close enough in time to cause delay for each other is a lot lesser, whereas it is guaranteed in the first example.
  - 3) In the third example we can lower the mean amount of customers in the queue further by focusing on finishing the jobs which take the least time.
  For example, let us say we have one A and one B job in the queue. Serving B first and then A will increase the time spent for the B job by 0.004s and the A job by 0.006s. Serving the A job first and then the B job increases the time spent for the A job by 0.002s and the B job by 0.006s. On average we therefore save 0.002 seconds for every 2 jobs for every time they are inserted.

