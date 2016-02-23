module Primes where
import Divisors

isPrime :: Int -> Bool
isPrime x = length (divisors x) == 2 || x == 1

primes :: Int -> [Int]
primes x = take x (filter isPrime [1..])

-- I have the feeling that there is another approach without filter and [1..]
