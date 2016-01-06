module Factorial (fact) where
	fact :: Int -> Int
	fact 0 = 1
	fact x = x * fact (x - 1)
