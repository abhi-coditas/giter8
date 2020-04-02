package giter8

import java.io.File
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GitRepositoryTest extends AnyFlatSpec with Matchers {

  "JGit" should "resolve repo name correctly" in {
    val testCases: Map[String, GitRepository] = Map(
      "git@some.path.com/repo" -> GitRepository.Remote("git@some.path.com/repo"),
      "git://some.path.com/repo" -> GitRepository.Remote("git://some.path.com/repo"),
      "https://some.path.com/repo" -> GitRepository.Remote("https://some.path.com/repo"),
      "http://some.path.com/repo" -> GitRepository.Remote("http://some.path.com/repo"),
      "ssh://some.path.com/repo" -> GitRepository.Remote("ssh://some.path.com/repo"),
      "file://relative/path" -> GitRepository.Local(new File("relative/path")),
      "file:///home/foo/bar" -> GitRepository.Local(new File("/home/foo/bar")),
      "foo/bar" -> GitRepository.GitHub("foo", "bar")
    )

    testCases foreach { testCase =>
      val string   = testCase._1
      val expected = testCase._2
      GitRepository.fromString(string) shouldBe Right(expected)
    }
  }

}
