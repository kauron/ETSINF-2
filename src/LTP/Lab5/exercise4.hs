import Shape
main = do
    let circle = circle 1
    let rect   = rectangle 3 4
    putStrLn ("The circle has perimeter " ++ (perimeter circle))
    putStrLn ("The rectangle has perimeter " ++ (perimeter rect))
