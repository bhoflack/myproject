(ns project.page-helpers
  (:use hiccup.core
        hiccup.page-helpers))

(defn
  #^{:doc "Standard page template."}
  page [title body]
  (html
   (doctype :xhtml-strict)
   [:head
    [:title title]]
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
