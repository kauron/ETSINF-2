import Stack
import StackLists

main = do
  let list = [1,2,3,4,5]
  putStrLn (show list)
  putStrLn (show (fromList list))
  putStrLn (show (toList (fromList list)))
  putStrLn (show ((toList (fromList list)) == list))
  let stack = push 15 (push 10 (push 5 empty))
  putStrLn (show stack)
  putStrLn (show (toList stack))
  putStrLn (show (fromList (toList stack)))
  putStrLn (show ((fromList (toList stack)) == stack))
