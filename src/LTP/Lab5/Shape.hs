module Shape (
      area, perimeter, circle, rectangle, volumePrism, surfacePrism, (==), (/=), show
    ) where
  type Height = Float
  type Width  = Float
  type Radius = Float
  data Rectangle  = Rectangle Height Width
  data Circle = Circle Radius

  class (Eq a, Show a) => Shape a where
     area :: a -> Float
     perimeter :: a -> Float
     show :: a -> String
     rectangle :: Height -> Width -> a
     circle :: Radius -> a
     (==) :: a -> a -> Bool
     (/=) :: a -> a -> Bool

  instance Shape Rectangle where
     area (Rectangle h w) = h * w
     perimeter (Rectangle h w) = 2 * (h + w)
     show (Rectangle x y) = ("Rectangle (" ++ (show x) ++ " X " ++ (show y) ++ ")")
     rectangle x y = Rectangle x y

  instance Shape Circle where
     area (Circle r) = pi * r**2
     perimeter (Circle r) = 2 * pi * r
     show (Circle r) = ("Circle (r = " ++ (show r) ++ ")")
     circle r = Circle r

  type Volume = Float
  volumePrism :: (Shape a) => a -> Height -> Volume
  volumePrism base height = (area base) * height

  surfacePrism :: (Shape a) => a -> Height -> Float
  surfacePrism base height = (area base) * 2 + height * (perimeter base)
