# ConcurrentColoredBoard
A board of given size filled with colored rectangles and then (depending on possibility) either randomly changes their color or sets it to an average of their 4 neighbors. First Try on Communicating Sequential Proccesses

## Parameters 
- Both columns and rows should be unsigned (positive) integers (not to large, say 10-80);
- Delay is set in mil. secs so should be between 100-5000 (500 works fine);
- Possibility (as usual) ranges from 1 (0 makes no sens) to 100, whereas j means that a rectangle
  will get a new random color with possibility of j/100, and share its color with neighbors with
  possibility of (1 - j/100)
