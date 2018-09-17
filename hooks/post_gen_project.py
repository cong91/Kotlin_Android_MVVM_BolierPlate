#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os
import shutil

project_dir = os.getcwd()

package_dir = '{{ cookiecutter.package_name }}'.replace('.', '/')

os.chdir(os.path.join("app","src","androidTest","java"))
os.makedirs(package_dir)
shutil.move('ApplicationTest.kt', package_dir + '/ApplicationTest.kt')

os.chdir(os.path.join(project_dir,"app","src","main","java"))
os.makedirs(package_dir)


os.chdir(os.path.join(project_dir,"app","src","test","java"))
os.makedirs(package_dir)
shutil.move('ExampleUnitTest.kt', package_dir + '/ExampleUnitTest.kt')

os.chdir(os.path.join(project_dir,"app","src","main","java",package_dir))
os.chdir(os.path.join(project_dir,"app","src","main","java"))

root_dst_dir = os.path.join(project_dir,"app","src","main","java",package_dir)
root_src_dir = os.path.join(project_dir,"app","src","main","java")

if '{{ cookiecutter.splash_screen }}' != 'y':
    shutil.rmtree('ui/splash')

if '{{ cookiecutter.retrofit }}' != 'y':
    os.remove('di/module/NetModule.kt')
    os.remove('utils/service/CallbackWrapper.kt')
    os.remove('db/dao/PostDao.kt')
    os.remove('db/entities/Post.kt')
    os.remove('network/PostApi.kt')
    os.remove('network/PostRepository.kt')
    os.remove('../res/layout/activity_post_list.xml')
    os.remove('../res/layout/item_post.xml')
    shutil.rmtree('ui/list')
    shutil.rmtree('core/api')

if '{{ cookiecutter.login }}' != 'y':
    os.remove('../res/drawable/bg_input_cursor.xml')
    os.remove('../res/drawable/bg_input_cursor_2.xml')
    os.remove('../res/drawable/bt_shape.xml')
    os.remove('../res/drawable/bt_shape_2.xml')
    os.remove('../res/drawable/selector_bg_edit.xml')
    os.remove('../res/drawable/selector_bg_edit_2.xml')
    os.remove('../res/drawable/state_list_animator_z.xml')
    os.remove('../res/drawable-xxxhdpi/plus.png')
    os.remove('../res/drawable-xxxhdpi/plus_x.png')
    os.remove('../res/layout/activity_login.xml')
    os.remove('../res/layout/activity_register.xml')
    shutil.rmtree('ui/login')

	
	
base = os.path.join(root_src_dir,"core")
di = os.path.join(root_src_dir,"di")
utils = os.path.join(root_src_dir,"utils")
ui = os.path.join(root_src_dir,"ui")
db = os.path.join(root_src_dir,"db")
network = os.path.join(root_src_dir,"network")

shutil.move('App.kt',os.path.join(project_dir,"app","src","main","java",package_dir,'App.kt'))

def moverecursively(source_folder, destination_folder):
    basename = os.path.basename(source_folder)
    dest_dir = os.path.join(destination_folder, basename)
    if not os.path.exists(dest_dir):
        shutil.move(source_folder, destination_folder)
    else:
        dst_path = os.path.join(destination_folder, basename)
        for root, dirs, files in os.walk(source_folder):
            for item in files:
                src_path = os.path.join(root, item)
                if os.path.exists(dst_file):
                    os.remove(dst_file)
                shutil.move(src_path, dst_path)
            for item in dirs:
                src_path = os.path.join(root, item)
                moverecursively(src_path, dst_path)

moverecursively(base,root_dst_dir)
moverecursively(di,root_dst_dir)
moverecursively(utils,root_dst_dir)
moverecursively(ui,root_dst_dir)
moverecursively(db,root_dst_dir)
moverecursively(network,root_dst_dir)

