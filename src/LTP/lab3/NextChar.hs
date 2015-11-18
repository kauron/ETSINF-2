module NextChar where
  import Data.Char
  nextchar :: Char -> Char
  nextchar c = chr ((ord c) + 1)
