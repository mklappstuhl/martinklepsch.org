(ns mkl.posts
  (:require [mkl.pods]
            [mkl.frontmatter :as fm]
            [pod.retrogradeorbit.bootleg.markdown :as markdown]
            [pod.retrogradeorbit.bootleg.glob :as glob]))

(def post-files
  (sort (glob/glob "content/posts/*.md")))

(def onehundred-files
  (sort (glob/glob "content/onehundred/*.md")))

(defn read-post [file]
  {:file file
   :frontmatter (fm/get-frontmatter file)
   :source (slurp file)
   ;; :content to be compatible with view code
   :content (-> file fm/file-contents second (markdown/markdown :data))})

(defn sort-posts [posts]
  (->> posts (sort-by (comp :date-published :frontmatter)) reverse))

(def test-post
  (read-post (first post-files)))

(defn -main []
  (read-post (first post-files)))

;; TODO registry
;; maintain a list of posts in memory
;; when files change reread the post data for the post with the same :file key

(comment
  (read-post (first post-files))
  (def file (first post-files))
  (-> file fm/file-contents second (markdown/markdown :data :html))

  )
