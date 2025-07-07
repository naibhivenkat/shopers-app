from flask import Blueprint, jsonify
from models import Order, User

shopowner_bp = Blueprint('shopowner', __name__)

@shopowner_bp.route('/get_orders/<int:shop_id>', methods=['GET'])
def get_orders(shop_id):
    orders = Order.query.filter_by(shop_id=shop_id).all()
    result = []
    for order in orders:
        customer = User.query.get(order.customer_id)
        result.append({
            'order_id': order.id,
            'customer': customer.username,
            'status': order.status
        })
    return jsonify(result)
