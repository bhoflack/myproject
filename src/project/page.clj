(ns project.page
  (:use project.model
        hiccup.core
        hiccup.page-helpers))

(defn-
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

(defn-
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
  #^{:doc "Page containing a list of all projects."}
  projects-page [projects]
  (page "projects"
        (html
         [:table
          [:tr
           [:th "name"]
           [:th "version"]
           [:th "actions"]]
          (map (fn [project]
                 (html
                  [:tr
                   [:td (:name project)]
                   [:td (:version project)]
                   [:td (link-to (str "/project/"
                                      (:name project) "/"
                                      (:version project))
                                 "detail")]]))
               projects)])))

(defn
  #^{:doc "Page containing a detailed view of a project."}
  project-detail-page [project]
  (page (short-description project)
        (html
         (key-value-table [:name :version] project))))
