var gulp = require('gulp');
var less = require('gulp-less');
var connect = require('gulp-connect');
var runSequence = require('run-sequence');

gulp.task('less', function () {
    return gulp.src([
            'less/main.less'
        ])
        .pipe(less().on('error', function(err){
            console.error(err.message);
            this.emit('end');
        }))
        .pipe(gulp.dest('assets/css'));
});

gulp.task('watch:less', function () {
    return gulp.watch('less/**/*.less', ['less']);
});


gulp.task('connect', function () {
    connect.server({
        port: 3001
    });
});


gulp.task('dev', function () {
    runSequence(
        ['less'],
        ['watch:less', 'connect']
    )
});