module BookDataBase where

type Person = String
type Book = String
type Database = [(Person,Book)]

exampleBase :: Database
exampleBase = [("Alicia", "El nombre de la rosa"),("Juan", "La hija del canibal"),("Pepe", "Odesa"),("Alicia", "La ciudad de las bestias")]

obtain :: Database -> Person -> [Book]
obtain dBase thisPerson = [ book | (person,book) <- dBase, person == thisPerson ]

borrow :: Database -> Book -> Person -> Database
borrow d b p = d ++ [(p,b)]

return' :: Database -> (Person,Book) -> Database
return' d (p,b) = [ (person,book) | (person,book) <- d, p /= person, b /= book ]
