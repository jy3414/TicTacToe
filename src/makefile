JFLAGS = -g
JC = javac
JVM = java
FILE =
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
	TicTacToe.java \
	AIPlayer.java \
	HumanPlayer.java \
	Board.java \
	CommandLineUI.java \
	Move.java \
	MoveWithScore.java \
	Player.java \
	UI.java

MAIN = TicTacToe

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class