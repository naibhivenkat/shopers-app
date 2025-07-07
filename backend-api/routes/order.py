from flask import Blueprint, request, jsonify
from models import db, Order, User, Shop

order_bp = Blueprint('order', __name__)

@order_bp.route("/place_order", methods=["POST"])
def place_order():
    data = request.json
    order = Order(
        customer_id=data['customer_id'],
        shop_id=data['shop_id'],
        items=data['items'],  # send as JSON string
        status="pending"
    )
    db.session.add(order)
    db.session.commit()
    return jsonify({"status": "success", "order_id": order.id})

@order_bp.route("/get_orders/<int:shop_id>", methods=["GET"])
def get_orders(shop_id):
    orders = Order.query.filter_by(shop_id=shop_id).all()
    response = []
    for order in orders:
        response.append({
            "id": order.id,
            "customer_id": order.customer_id,
            "items": order.items,
            "status": order.status
        })
    return jsonify(response)
