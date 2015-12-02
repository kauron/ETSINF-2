module Shape2 where
  type Height = Float
  type Width  = Float
  type Radius = Float

  data Shape = Rectangle Height Width | Circle Radius deriving (Eq,Show)

  area :: Shape -> Float
  area (Rectangle h w) = h * w
  area (Circle r) = pi * r**2

  perimeter :: Shape -> Float
  perimeter (Rectangle h w) = 2 * (h + w)
  perimeter (Circle r) = 2 * pi * r

  type Volume = Float
  volumePrism :: Shape -> Height -> Volume
  volumePrism base height = (area base) * height

  surfacePrism :: Shape -> Height -> Float
  surfacePrism base height = (area base) * 2 + (perimeter base) * height
