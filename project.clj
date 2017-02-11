(defproject trivia "0.0.1-SNAPSHOT"
  :description "Trivia Game"
  :url "heebo.mooo.com"
  :license {:name "GPLv3" :url "http://choosealicense.com/license/gpl-3.0/#"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [reagent "0.6.0"]
                 [reagent-utils "0.2.0"]]
  :plugins [[lein-cljsbuild "1.1.3"]]
  :min-lein-version "2.6.1"
  :main ^:skip-aot trivia.core
  :target-path "target/%s"
  :source-paths ["src/clj" "src/cljs"]
  :test-paths ["test/clj"]
  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                "target"
                                "test/js"]

  :figwheel {:css-dirs ["resources/public/css"]}
  :profiles {:uberjar{:aot :all}
             :dev {:dependencies [[figwheel-sidecar "0.5.4-7"]
                                  [com.cemerick/piggieback "0.2.1"]
                                  [binaryage/devtools "0.8.0"]]
                   :plugins [[lein-figwheel "0.5.4-7"]
                             [lein-doo "0.1.7"]]}}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs"]
                        :figwheel {:on-jsload "trivia.core/mount-root"}
                        :compiler {:main trivia.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :source-map-timestamp true}}
                       ;; necessary to run lein doo phanntom test
                       ;; from https://github.com/bensu/doo
                       {:id "test"
                        :source-paths ["src/cljs" "test/cljs"]
                        :figwheel {:on-jsload "trivia.core/mount-root"}
                        :compiler {:main trivia.runner
                                   :output-to "resources/public/js/compiled/test.js"
                                   :optimizations :none}}]})
