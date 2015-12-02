-- *****************************
-- Exercises 
-- Practice 5 of LTP
-- *****************************

-------------------------------------------------------------------
-- WARNING:
-- 	ESTE FICHERO SE PROPORCIONA PARA FACILITAR
--	LA COPIA DE LINEAS DE CODIGO DE AQUI AL PROGRAMA DEL ALUMNO
--      ESTE FICHERO NO COMPILA
--	EL INTERPRETE DE HASKELL GENERA UN PARSE ERROR
--	SI SE EJECUTA :load codigoEnPdf_P5
-------------------------------------------------------------------


-- ********************************
-- *** Section 2.2. export list ***
-- ********************************

-- Geometry2D.hs
module Geometry2D
( areaSquare
, perimeterSquare
) where

areaRectangle :: Float -> Float -> Float
areaRectangle base height = base * height

perimeterRectangle :: Float -> Float -> Float
perimeterRectangle base height = 2*(base+height)

areaSquare :: Float -> Float
areaSquare side = areaRectangle side side

perimeterSquare :: Float -> Float
perimeterSquare side = perimeterRectangle side side

-- test.hs
import Geometry2D
-- main = putStrLn ("The area is " ++ show (areaRectangle 2 3))
main = putStrLn ("The area is " ++ show (areaSquare 2))


-- **************************************
-- *** Section 2.3. qualified imports ***
-- **************************************

-- NormalizeSpaces.hs
module NormalizeSpaces where
  normalize :: String -> String
  normalize = unwords . words

-- NormalizeCase.hs
module NormalizeCase where
  import Data.Char (toLower) -- import only function toLower
  normalize :: String -> String
  normalize = map toLower

-- NormalizeAll.hs
module NormalizeAll where
  import qualified NormalizeSpaces
  import qualified NormalizeCase
  normalizeAll :: String -> String
  normalizeAll = NormalizeSpaces.normalize . NormalizeCase.normalize

  
-- ********************************************
-- *** Section 3.1. parametric polymorphism ***
-- ********************************************

length :: [a] -> Int
length [] = 0
length (x:xs) = 1 + length xs

(==) :: [a] -> [a] -> Bool
[]     == []     = True
[]     == (x:xs) = False
(x:xs) == []     = False
(x:xs) == (y:ys) = x==y && xs==ys

(==) :: (Eq a) => [a] -> [a] -> Bool

-- module Stack v1
module Stack (Stack, empty, push, pop, top, isEmpty) where
data Stack a       = EmptyStack | Stk a (Stack a)
push x s           = Stk x s
top (Stk x s)      = x
pop (Stk _ s)      = s
empty              = EmptyStack
isEmpty EmptyStack = True
isEmpty (Stk x s)  = False

-- testStack.hs
import Stack
main = putStrLn show(isEmpty (EmptyStack))

-- testStack2.hs
import Stack
main = putStrLn (show (top (push 5 empty)))

-- module Stack v2
module Stack (Stack, empty, push, pop, top, isEmpty) where
data Stack a     = Stk [a]
empty            = Stk []
push x (Stk xs)  = Stk (x:xs)
pop (Stk (x:xs)) = Stk xs
top (Stk (x:xs)) = x
isEmpty (Stk xs) = null xs

-- testStack3.hs
import Stack
main = putStrLn (show (push 7 (push 5 empty)))

-- instance Show ...
instance (Show a) => Show (Stack a) where
   show EmptyStack = "|"
   show (Stk x y) = (show x) ++ " <- " ++ (show y)

-- testStack4.hs
import Stack
main = do
   putStrLn (show (pop (push 1 empty)))
   putStrLn (show (push 10 (push 5 empty)))


-- ****************************************
-- *** Section 3.2. ad hoc polymorphism ***
-- ****************************************

-- v1 (without type classes)

type Height = Float
type Width  = Float
type Radius = Float
data Shape  = Rectangle Height Width |
              Circle Radius
              deriving (Eq, Show)
area :: Shape -> Float
area (Rectangle h w) = h * w
area (Circle r) = pi * r**2


-- v2 (with type classes)

type Height = Float
type Width  = Float
type Radius = Float
data Rectangle  = Rectangle Height Width 
data Circle = Circle Radius

class Shape a where
   area :: a -> Float

instance Shape Rectangle where
   area (Rectangle h w) = h * w

instance Shape Circle where
   area (Circle r) = pi * r**2

type Volume = Float
volumePrism :: (Shape a) => a -> Height -> Volume
volumePrism base height = (area base) * height
