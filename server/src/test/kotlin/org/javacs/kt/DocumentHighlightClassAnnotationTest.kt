package org.javacs.kt

import org.eclipse.lsp4j.DocumentHighlightKind
import org.eclipse.lsp4j.DocumentHighlightParams
import org.eclipse.lsp4j.Position
import org.eclipse.lsp4j.TextDocumentIdentifier
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Test

class DocumentHighlightClassAnnotationTest : SingleFileTestFixture("highlight", "DocumentHighlightClassAnnotation.kt") {

    @Test
    fun `should highlight class annotation reference`() {
        val fileUri = workspaceRoot.resolve(file).toUri().toString()
        val input = DocumentHighlightParams(TextDocumentIdentifier(fileUri), Position(2, 2))
        val result = languageServer.textDocumentService.documentHighlight(input).get()

        assertThat(result, hasSize(2))
        val declarationHighlight = result[0]
        assertThat(declarationHighlight.range, equalTo(range(1, 18, 1, 24)))
        assertThat(declarationHighlight.kind, equalTo(DocumentHighlightKind.Text))

        val usageHighlight = result[1]
        assertThat(usageHighlight.range, equalTo(range(3, 2, 3, 8)))
        assertThat(usageHighlight.kind, equalTo(DocumentHighlightKind.Text))
    }
}
