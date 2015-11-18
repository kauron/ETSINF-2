module SearchInLists where

member :: Int -> [Int] -> Bool
member x [] = False
member x (y:ys)
	| x == y = True
	| otherwise = member x ys
