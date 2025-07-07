from flask import Flask
from flask_cors import CORS
from routes.auth import auth_bp
from routes.shop import shop_bp
from routes.order import order_bp

app = Flask(__name__)
CORS(app)

app.config['SECRET_KEY'] = 'shoppersappsecret'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///shopersapp.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

# Register routes
app.register_blueprint(auth_bp)
app.register_blueprint(shop_bp)
app.register_blueprint(order_bp)

if __name__ == "__main__":
    app.run(debug=True)
