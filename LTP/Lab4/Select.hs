module Select where

selectEven :: [Int] -> [Int]
selectEven (x:xs)
	| length xs == 0 && even x = [x]
	| length xs == 0 = []
	| even x = x : (selectEven xs)
	| otherwise = selectEven xs

selectEvenPos :: [Int] -> [Int]
selectEvenPos (x:[]) = [x]
selectEvenPos (x0:(x1:xs)) = x0 : (selectEvenPos xs)
