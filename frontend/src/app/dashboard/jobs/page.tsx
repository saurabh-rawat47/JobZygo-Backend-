'use client';

import { useState, useEffect } from 'react';
import { jobsAPI } from '@/lib/api';
import { JobPost, User } from '@/types';
import JobSearch from '@/components/jobs/JobSearch';
import JobCard from '@/components/jobs/JobCard';
import Button from '@/components/ui/Button';
import { Plus, Filter } from 'lucide-react';

export default function JobsPage() {
  const [jobs, setJobs] = useState<JobPost[]>([]);
  const [filteredJobs, setFilteredJobs] = useState<JobPost[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [user, setUser] = useState<User | null>(null);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    const savedUser = localStorage.getItem('user');
    if (savedUser) {
      setUser(JSON.parse(savedUser));
    }

    fetchJobs();
  }, []);

  const fetchJobs = async () => {
    try {
      setIsLoading(true);
      const allJobs = await jobsAPI.getAllJobs();
      setJobs(allJobs);
      setFilteredJobs(allJobs);
    } catch (error) {
      console.error('Error fetching jobs:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleSearch = async (searchText: string) => {
    setSearchTerm(searchText);
    
    if (!searchText.trim()) {
      setFilteredJobs(jobs);
      return;
    }

    try {
      const searchResults = await jobsAPI.searchJobs(searchText);
      setFilteredJobs(searchResults);
    } catch (error) {
      console.error('Error searching jobs:', error);
      // Fallback to client-side search
      const filtered = jobs.filter(job =>
        job.profile.toLowerCase().includes(searchText.toLowerCase()) ||
        job.companyName.toLowerCase().includes(searchText.toLowerCase()) ||
        job.location.toLowerCase().includes(searchText.toLowerCase()) ||
        job.techs.some(tech => tech.toLowerCase().includes(searchText.toLowerCase()))
      );
      setFilteredJobs(filtered);
    }
  };

  const handleClearSearch = () => {
    setSearchTerm('');
    setFilteredJobs(jobs);
  };

  const handleApplyJob = (jobId: string) => {
    // TODO: Implement job application logic
    alert('Application feature coming soon!');
  };

  if (isLoading) {
    return (
      <div className="flex items-center justify-center min-h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
      </div>
    );
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex justify-between items-center">
        <div>
          <h1 className="text-3xl font-bold text-gray-900">Job Opportunities</h1>
          <p className="text-gray-600 mt-2">
            Discover {filteredJobs.length} amazing opportunities
          </p>
        </div>
        {user?.userType === 'employer' && (
          <Button
            onClick={() => window.location.href = '/dashboard/create-job'}
            className="flex items-center"
          >
            <Plus className="w-4 h-4 mr-2" />
            Post Job
          </Button>
        )}
      </div>

      {/* Search */}
      <JobSearch
        onSearch={handleSearch}
        onClear={handleClearSearch}
        isLoading={false}
      />

      {/* Results */}
      <div className="bg-white rounded-lg shadow-sm p-6">
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-xl font-semibold text-gray-900">
            {searchTerm ? `Search Results for "${searchTerm}"` : 'All Jobs'}
          </h2>
          <div className="flex items-center space-x-2 text-sm text-gray-600">
            <Filter className="w-4 h-4" />
            <span>{filteredJobs.length} jobs found</span>
          </div>
        </div>

        {filteredJobs.length > 0 ? (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {filteredJobs.map((job, index) => (
              <JobCard
                key={job.id || `job-${index}`}
                job={job}
                onApply={user?.userType === 'jobseeker' ? handleApplyJob : undefined}
              />
            ))}
          </div>
        ) : (
          <div className="text-center py-12">
            <div className="w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-4">
              <Filter className="w-8 h-8 text-gray-400" />
            </div>
            <h3 className="text-lg font-medium text-gray-900 mb-2">No jobs found</h3>
            <p className="text-gray-600 mb-4">
              {searchTerm 
                ? `No jobs match your search for "${searchTerm}". Try different keywords.`
                : 'No jobs are currently available.'
              }
            </p>
            {searchTerm && (
              <Button
                onClick={handleClearSearch}
                variant="outline"
              >
                Clear Search
              </Button>
            )}
          </div>
        )}
      </div>
    </div>
  );
}


