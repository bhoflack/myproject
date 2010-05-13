(ns project.page
  (:use project.model
        project.page-helpers
        hiccup.core
        hiccup.page-helpers))

(defn
  #^{:doc "Page containing a list of all projects."}
  projects-page [projects]
  (page "projects"
        (html
         (list-table
          (fn [project]
            (html (link-to (str "/project/" (:name project) "/" (:version project) "/") "detail")))
          '(:name :version)
          projects))))

(defn
  #^{:doc "Page containing a detailed view of a project."}
  project-detail-page [project]
  (page (short-description project)
        (html
         (key-value-table [:name :version :customer :nickname] project))))

(defn
  #^{:doc "Page containing a list of all questionnaire-templates."}
  questionnaire-templates-page [questionnaire-templates]
  (page "questionnaire templates"
        (html
         (list-table
          (fn [qt]
            (html (link-to (str "/questionnaire/" (:shortname qt) "/" (:version qt) "/") "detail")))
          '(:name :shortname :version)
          questionnaire-templates))))

(defn
  #^{:doc "Page containing a detailed view of a questionnaire-template"}
  questionnaire-template-detail-page [qt]
  (page (str (:name qt) " " (:version qt))
        (html 
         (key-value-table [:name :shortname :version] qt)
         (sortable-unordered-list (map (fn [q] (:question q))
                                       (:questions qt))))))

