//倒计时，输入时间的毫秒数
(function ($) {

    $.fn.downCount = function (options, callback) {
        var settings = $.extend({
                date: null
            }, options);

        // Save container
        var container = this;

        var difference = settings.date;

        function countdown () {
            var target_date = settings.date, // set target date
                current_date = Date.parse(new Date()); // get fixed current date

            // difference of dates
            //var difference = target_date - current_date;
            //var difference = target_date;

            // if difference is negative than it's pass the target date
            if (difference < 0) {
                // stop timer
                clearInterval(interval);

                if (callback && typeof callback === 'function') callback();

                return;
            }

            // basic math variables
            var _second = 1000,
                _minute = _second * 60,
                _hour = _minute * 60,
                _day = _hour * 24;

            // calculate dates
            var days = Math.floor(difference / _day);
            var hours = Math.floor((difference % _day) / _hour) + days *24,
                minutes = Math.floor((difference % _hour) / _minute),
                seconds = Math.floor((difference % _minute) / _second);

                // fix dates so that it will show two digets
                //days = (String(days).length >= 2) ? days : '0' + days;
                hours = (String(hours).length >= 2) ? hours : '0' + hours;
                minutes = (String(minutes).length >= 2) ? minutes : '0' + minutes;
                seconds = (String(seconds).length >= 2) ? seconds : '0' + seconds;

            // based on the date change the refrence wording
            // var ref_days = (days === 1) ? 'day' : 'days',
            //     ref_hours = (hours === 1) ? 'hour' : 'hours',
            //     ref_minutes = (minutes === 1) ? 'minute' : 'minutes',
            //     ref_seconds = (seconds === 1) ? 'second' : 'seconds';

            // set to DOM
            //container.find('.days').text(days);
            container.find('.hours').text(hours);
            container.find('.minutes').text(minutes);
            container.find('.seconds').text(seconds);

            // container.find('.days_ref').text(ref_days);
            // container.find('.hours_ref').text(ref_hours);
            // container.find('.minutes_ref').text(ref_minutes);
            // container.find('.seconds_ref').text(ref_seconds);
        };
        
        // start

        function countdowninterval(){
            if(difference <= 0){
                location.reload();
            }
            countdown();
            difference = difference - 1000;
        }
        countdowninterval();
        var interval = setInterval(countdowninterval, 1000);
    };

})(jQuery);