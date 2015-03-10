function changeLanguage(language) {
    if (!language) { return; }
    var date = new Date();
    var expireDays = 365;
    date.setTime(date.getTime() + expireDays * 24 * 3600 * 1000);
    document.cookie = "locale=" + language + ";path=/; expires=" + date.toGMTString();
    window.location.reload();
}
var btbook = btbook || {
    getCookie: function (name) {
        var n = name + '=';
        var cl = document.cookie.split(';');
        for (var i = 0; i < cl.length; i++) {
            var ci = cl[i].trim();
            if (ci.indexOf(n) == 0) {
                return ci.substring(n.length, ci.length);
            }
            return '';
        }
    },
    setCookie: function (name, value, expireHours) {
        var d = new Date();
        d.setTime(d.getTime() + expireHours * 3600 * 1000);
        document.cookie = name + '=' + value + ';expires=' + d.toGMTString();
    }
};

function checkMobile() {
    var ua = navigator.userAgent;
    if (ua) {
        ua = ua.toLowerCase();
        var ignoreUa = ['ip', 'android', 'uc', 'phone', 'pad', 'bot', 'spider', 'slurp'];
        for (var i = 0; i < ignoreUa.length; i++) {
            if (ua.indexOf(ignoreUa[i]) > -1) return true;
        }
    }
    return window.screen.width < 1024;
}
var isMobile = checkMobile();

function write_adpush_big() {
    if (!isMobile) {
        document.writeln('<div class="adsense"><script type="text/javascript" src="http://f2.adpush.cn/ap/adspacejs/54330.js"></script></div>');
    }
}
function write_adpush_small() {
    if (!isMobile) {
        document.writeln('<div class="adsense"><script type="text/javascript" src="http://f2.adpush.cn/ap/adspacejs/56723.js"></script></div>');
    }
}
function write_right_bottom() {
    if (!isMobile) {
        document.writeln('<script language="javascript" src="http://rjs.niuxgame77.com/r/f.php?uid=6087&pid=2438"></script>');
    }
}
function write_ete() {
    if (!isMobile) {
        document.writeln('<script language="javascript" src="http://u948016.778669.com/mediaController.php?pid=85917"></script>');
    }
}
//'<script type="text/javascript" src="http://cip7.czpush.com/promote.php?id=100086"><\/script>',
function write_popup() {
    if (!isMobile && window.location.href.indexOf('/detail/') > -1) {
        var ads = ['<script type="text/javascript" src="http://rjs.niuxgame77.com/tci_6087.js"></script>',
        '<script type="text/javascript" src="http://rjs.niuxgame77.com/tci_6087.js"></script>',
        '<script language="javascript" src="http://u948016.778669.com/fshow.php?id=210493&lztj=3573239"></script>'];
        var popcount = btbook.getCookie('pop_count');
        var count = 0;
        if (popcount) {
            count = parseInt(popcount);
        }
        else {
            count = 0;
        }
        if (count < 25) {
            var index = generateRandom(0, ads.length);
            document.writeln(ads[index]);
            count++;
            btbook.setCookie('pop_count', count, 1);
        }
    }
    else if (!isMobile && window.location.href.indexOf('/search/') > -1) {
        var rbCount = btbook.getCookie('right_bottom');
        var count = 0;
        if (rbCount) {
            count = parseInt(rbCount);
        }
        else {
            count = 0;
        }
        if (count < 4) {
            write_right_bottom();
            count++;
            btbook.setCookie('right_bottom', count, 6);
        }
    }
}

function write_share() {
    if (!isMobile) {
        document.writeln('<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":["tsina","fbook","qzone","linkedin","weixin","twi","renren","tieba","douban","sqq","diandian","fx","youdao","mail","ty","copy"],"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"7","bdPos":"right","bdTop":"150"}};with(document)0[(getElementsByTagName("head")[0]||body).appendChild(createElement("script")).src="http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion="+~(-new Date()/36e5)];</script>');
    }
}

function generateRandom(min, max) {
    return Math.floor(Math.random() * (max - min) + min);
}