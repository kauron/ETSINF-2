module Exercise12 where

map' :: (a -> b) -> [a] -> [b]
map' f x = [ f y | y <- x]

filter' :: (a -> Bool) -> [a] -> [a]
filter' f x = [ y | y <- x, f y ]
