module Repetition where

repeated :: Int -> [Int] -> Int
repeated x [] = 0
repeated x (y:ys)
	| y == x = 1 + (repeated x ys)
	| otherwise = repeated x ys
