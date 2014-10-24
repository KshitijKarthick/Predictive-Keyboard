# Predictive Keyboard - Proof of Concept :
## Program Features :
   * Gives a GUI for a Keyboard, which employs a Predictive Algorithm.
   * The Predictive Algorithm predicts words based on specific user vocabulary and sets ranks for each of the words.
   * Rank Based Predictive Algorithm which keeps evolving based on the Usage.
   * Accuracy of the Algorithm improves based on the usage.

## Program Details :
   * Program is written in Java.
   * Employs trie's to store and search for the word.
   * Selects 12 predictions from all the words with the highest Rank.
   * Rank of a word increases based on the usage of the word.
   * Searching for a word will require n iterations [ n <= wordlength ].
   * A GUI interface which then maps the buttons with respective characters and used to type in for prediction.
   * Conventional keyboard can be used for typing.
   * On Screen Keyboard can be used for typing.
   * Special Buttons facility is available.
   * Change of Case facility is available.
   * Dictionary.txt stores all the words used.

## Program Execution :
```
  # Windows and Posix OS Compliant.
  > cd Predictive-Keyboard/dist
  > java -jar Predictive-Keyboard
```
## To Do :
  * Add larget set of special symbols.
  * Add numeric symbols.
  * Imporovement in Rank Storage.
  * GUI keys should change from lower case to upper case.
