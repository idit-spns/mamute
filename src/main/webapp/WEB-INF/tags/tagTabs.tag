<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@attribute type="org.mamute.model.Tag" name="tag" required="false"%>
<%@attribute type="java.lang.Boolean" name="hasAbout" required="true"%>
<%@attribute name="unansweredTagLinks" type="java.lang.Boolean" required="false"%>
<tags:tabs titleKey="" useSubheader="${true}">
	${t['tag_list.title']}
	<a href="${linkTo[ListController].withTag(tag.name)}">
		${tag.name}
	</a>
	<c:if test="${hasAbout}">
		<a href="${linkTo[TagPageController].showTagPage(tag.name)}">
			${t['about.link']}
		</a>
	</c:if>
	<a href="${linkTo[RankingController].tagRank(tag.uriName)}" title='${t['users.ranking.tag.title']} ${tag.name}' class="icon-award">
		${t['users.ranking.tag']}
	</a>

    <a class="subscribe-to-tag" href="#" data-url="${linkTo[TagPageController].toggleUserSubscribeTag(tag.name)}">
        ${t[tag.isUserSubscribed(currentUser.current) ? 'tag_page.unsubscribe' : 'tag_page.subscribe']}
    </a>
</tags:tabs>