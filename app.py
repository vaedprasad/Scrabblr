from flask import Flask
from flask import render_template
from flask import request
import json
from operator import itemgetter

app = Flask("Scrabblr")

@app.route("/")
def home():
    return render_template("index.html")

if __name__ == "__main__":
    app.run(debug=True)