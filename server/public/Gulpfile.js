var gulp = require('gulp');
var concat = require('gulp-concat');
var less = require('gulp-less');

gulp.task('concatJs', function () {
    gulp.src(['javascripts/app/app.js', 'javascripts/app/**/*.js'])
        .pipe(concat('app.concat.js'))
        .pipe(gulp.dest('./javascripts/'));
});

gulp.task('watchJs', function () {
    gulp.watch('javascripts/app/**/*.js', ['concatJs']);
});

gulp.task('less', function () {
    gulp.src('less/styles.less')
        .pipe(less())
        .pipe(gulp.dest('./css/'));
});

gulp.task('watchLess', function () {
    gulp.watch('less/**/*.less', ['less']);
});