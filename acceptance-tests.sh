#!/bin/bash
set -ex
gem install bundler
wget https://github.com/grace000/http-server-spec/archive/master.tar.gz
tar -xzvf master.tar.gz
cd http-server-spec-master && bundle install
bundle exec spinach