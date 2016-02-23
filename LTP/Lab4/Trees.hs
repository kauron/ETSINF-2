module Trees where

data Tree a = Leaf a | Branch (Tree a) (Tree a) deriving Show

numleaves :: Tree a -> Int
numleaves (Leaf x) = 1
numleaves (Branch a b) = numleaves a + numleaves b

symmetric :: Tree a -> Tree a
symmetric (Leaf a) = Leaf a
symmetric (Branch a b) = Branch (symmetric b) (symmetric a)

list2tree :: [a] -> Tree a
list2tree (x:[]) = Leaf x
list2tree (x:xs) = Branch (Leaf x) (list2tree xs)

tree2list :: Tree a -> [a]
tree2list (Leaf a) = [a]
tree2list (Branch a b) = tree2list a ++ tree2list b
