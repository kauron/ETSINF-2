module SortingHat where

iSort :: [Int] -> [Int]
iSort [] = []
iSort (x:[]) = ins x []
iSort (x:xs) = ins x (iSort xs)


ins :: Int -> [Int] -> [Int]
ins x [] = [x]
ins x (y:[])
	| x <= y = x : y : []
	| otherwise = y : x : []
ins x (y:ys)
	| x <= y = x : (y:ys)
	| otherwise = y : (ins x ys)
