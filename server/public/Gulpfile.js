var gulp = require('gulp');
var concat = require('gulp-concat');

gulp.task('concatJs', function () {
    gulp.src(['javascripts/app/app.js', 'javascripts/app/**/*.js'])
        .pipe(concat('app.concat.js'))
        .pipe(gulp.dest('./javascripts/'));
});