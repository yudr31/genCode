/**
 * NNK全局对象
 */
var NNK = NNK || {};
NNK.NavTab = {
    reloadCurrentNavTab: function () {
        navTab._reload(navTab._getTabs().eq(navTab._currentIndex), true);
    }
}