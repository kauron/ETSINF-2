module Divisors (divisors) where

divisors :: Int -> [Int]
divisors x = [ y | y <- [1..x], (mod x y) == 0]


oldDivisors :: Int -> [Int]
oldDivisors n = aux 1 n

aux :: Int -> Int -> [Int]
aux a n
	| n == a = [n]
	| n >= a && (mod n a) == 0 = a : (aux (a + 1) n)
	| n >= a = aux (a + 1) n
	| otherwise = []
