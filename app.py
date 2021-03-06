from flask import Flask
from flask import render_template
from flask import request
import json
import moveCalculator
from operator import itemgetter
from random import randint

app = Flask("Scrabblr")

class MoveRankings:
    def __init__(self):
        self.moves = []
        self.capacity = 10
    def fillRankings(self, boardState, tileRack):
        self.moves = moveCalculator.calcBestMoves(boardState, tileRack)

mr = MoveRankings()

for i in range(0,10):
    mr.moves.append(("Testing", randint(0,50)))

@app.route("/")
def home():
    return render_template("index.html", moves=mr.moves)

if __name__ == "__main__":
    app.run(debug=True)