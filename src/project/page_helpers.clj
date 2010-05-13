(ns project.page-helpers
  (:use hiccup.core
        hiccup.page-helpers))

(defn
  #^{:doc "Standard page template."}
  page [title body & [{js :js}]]
  (html
   (doctype :xhtml-strict)
   [:head
    [:title title]
    (include-js "http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"
                "http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"
                "/media/css/myproject.js")
    (javascript-tag js)]
   [:body
    [:h1 title]
    [:hr]
    body]))

(defn
  #^{:doc "Table view of an key value object.
           Items contains the keys to show,
           obj is the source hash."}
  key-value-table [items obj]
  (html
   [:table
    (map (fn [key]
           (html
            [:tr
             [:td key]
             [:td (key obj)]]))
         items)]))

(defn
  #^{:doc "A table view containing a list of objects."}
  list-table [f items objs]
  (html
   [:table
    [:tr
     (map (fn [item]
            (html
             [:th item]))
          items)
     [:th "actions"]]
    (map (fn [obj]
           (html
            [:tr
             (map (fn [item]
                    (html
                     [:td (item obj)]))
                  items)
            [:td (f obj)]]))
         objs)]))

(defn-
  #^{:doc "Create an list of maps with a position and the item"
     :test (fn []
             (assert (= [{:position 0 :item "hello"}
                         {:position 1 :item "world"}]
                        (order-list ["hello" "world"]))))}
  order-list [items]
  (map (fn [i p] {:position p :item i}) items (iterate inc 0)))

(defn
  #^{:doc "A sortable unordered list"}
  sortable-unordered-list [items]
  (html
   [:ul {:id "sortable"}
    (map #(html
           [:li {:id (str "items_" (:position %)) :class "ui-state-default"} (:item %)])
         (order-list items))]))
