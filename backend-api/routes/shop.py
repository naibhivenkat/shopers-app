from flask import Blueprint, jsonify
from models import db, Shop, Item

shop_bp = Blueprint('shop', __name__)

@shop_bp.route("/get_shops", methods=["GET"])
def get_shops():
    shops = Shop.query.all()
    data = []
    for shop in shops:
        items = Item.query.filter_by(shop_id=shop.id).all()
        item_list = [{
            "id": item.id,
            "name": item.name,
            "price": item.price,
            "icon": item.icon
        } for item in items]
        data.append({
            "id": shop.id,
            "name": shop.name,
            "items": item_list
        })
    return jsonify(data)
