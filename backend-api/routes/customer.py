from flask import Blueprint, jsonify
from models import Shop

customer_bp = Blueprint('customer', __name__)

@customer_bp.route('/get_shops', methods=['GET'])
def get_shops():
    shops = Shop.query.all()
    return jsonify([{'id': shop.id, 'name': shop.name} for shop in shops])
