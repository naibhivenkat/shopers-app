from flask import Blueprint, request, jsonify
from models import db, User

auth_bp = Blueprint('auth', __name__)

@auth_bp.route("/login", methods=["POST"])
def login():
    data = request.json
    user = User.query.filter_by(username=data["username"], password=data["password"]).first()
    if user:
        return jsonify({
            "status": "success",
            "user_id": user.id,
            "username": user.username,
            "role": user.role
        })
    else:
        return jsonify({"status": "fail", "message": "Invalid credentials"}), 401
