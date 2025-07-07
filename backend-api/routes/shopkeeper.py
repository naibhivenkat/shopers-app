# backend-api/routes/shopkeeper.py
from flask import Blueprint, request, jsonify
from models import db, Shop, Order, OrderItem, Item

shopkeeper_bp = Blueprint('shopkeeper', __name__)

@shopkeeper_bp.route('/orders/<int:shop_id>')
def get_shop_orders(shop_id):
    orders = Order.query.filter_by(shop_id=shop_id).all()
    result = []
    for o in orders:
        items = OrderItem.query.filter_by(order_id=o.id).all()
        item_details = [
            {'item': Item.query.get(i.item_id).name, 'qty': i.quantity}
            for i in items
        ]
        result.append({'order_id': o.id, 'status': o.status, 'items': item_details})
    return jsonify(result)

@shopkeeper_bp.route('/update_order_status', methods=['POST'])
def update_order_status():
    data = request.json
    order = Order.query.get(data['order_id'])
    if order:
        order.status = data['status']
        db.session.commit()
        return jsonify({'success': True})
    return jsonify({'success': False, 'message': 'Order not found'}), 404
