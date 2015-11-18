module DecimalBinary where

decBin :: Int -> [Int]
decBin x
	| x > 1 = (mod x 2) : ( decBin ( div x 2 ) )
	| otherwise = [x]

binDec :: [Int] -> Int
binDec (x:[]) = x
binDec (x:y) = x + binDec y * 2
