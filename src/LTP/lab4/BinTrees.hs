module BinTrees where

data BinTreeInt = Void | Node Int BinTreeInt BinTreeInt deriving Show

treeB1 = Void
treeB2 = Node 5 Void Void
treeB3 = Node 5 (Node 3 (Node 1 Void Void)(Node 4 Void Void)) (Node 6 Void (Node 8 Void Void))

-- exercise 18
insTree :: Int -> BinTreeInt -> BinTreeInt
insTree x (Void) = Node x Void Void
insTree x (Node y left right)
  | x > y = Node y left (insTree x right)
  | otherwise = Node y (insTree x left) right

-- exercise 19
creaTree :: [Int] -> BinTreeInt
creaTree (x:[]) = insTree x Void
creaTree (x:xs) = insTree x (creaTree xs)


-- exercise 20
treeElem :: Int -> BinTreeInt -> Bool
treeElem x Void = False
treeElem x (Node y left right)
  | x == y = True
  | x > y = treeElem x right
  | x < y = treeElem x left

-- exercise 21
dupTree :: BinTreeInt -> BinTreeInt
dupTree Void = Void
dupTree (Node x left right) = Node (x * 2) (dupTree left) (dupTree right)

-- exercise 22
countProperty :: (Int -> Bool) -> BinTreeInt -> Int
countProperty _ Void = 0
countProperty f (Node x left right)
  | f x = 1 + countProperty f left + countProperty f right
  | otherwise = countProperty f left + countProperty f right
