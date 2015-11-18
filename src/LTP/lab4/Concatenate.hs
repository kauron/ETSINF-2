module Concatenate where

concat' :: [[a]] -> [a]
concat' (x:xs)
	| length xs == 0 = x
	| otherwise = x ++ (concat' xs)
