module Exercises where
	import Data.Char
	import Factorial
	
	numCbetw2 :: Char -> Char -> Int
	numCbetw2 x y
		| x == y = 0
		| otherwise = abs ((ord y) - (ord x) - 1)

	sumFromAtoB :: Int -> Int -> Int
	sumFromAtoB a b
		| a == b = b
		| otherwise = a + sumFromAtoB (succ a) b
	-- sumFromAtoB a a = a, not 2a
	
	max' a b
		| a > b = a
		| otherwise = b

	leapyear :: Int -> Bool
	leapyear y = ((mod y 4) == 0 && (mod y 100) /= 0) || (mod y 400) == 0

	daysAMonth :: Int -> Int -> Int
	daysAMonth m y
		| m == 2 && leapyear y = 29
		| m == 2 = 28
		| m == 4 || m == 6 || m == 9 || m == 11 = 30
		| otherwise = 31

	remainder :: Int -> Int -> Int
	remainder x y
		| x >= y = remainder (x - y) y
		| otherwise = x
	
	sumFacts :: Int -> Int
	sumFacts a
		| a == 0 = fact a
		| otherwise = fact a + sumFacts (a - 1)
